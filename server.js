const express = require('express');
const puppeteer = require('puppeteer-extra');
const StealthPlugin = require('puppeteer-extra-plugin-stealth');
const fs = require('fs');
const app = express();
const PORT = 3000;

puppeteer.use(StealthPlugin());

let isScraping = false;  


async function autoScrollAndCapture(page, items, res) {
    let lastHeight = await page.evaluate('document.body.scrollHeight');
    let currentHeight;
    let scrollAttempts = 0;
    let itemsBeforeScroll = items.length;

    while (true) {
        console.log("Scrolling...");

        // Scroll down 
        await page.evaluate('window.scrollBy(0, window.innerHeight);');

        // Wait for new content to load
        await new Promise(resolve => setTimeout(resolve, 4000));  // 4-second wait for content

        // Capture items after each scroll
        const newItems = await getMenuItems(page);
        items.push(...newItems);  // Add new items

        // Remove duplicates 
        items = Array.from(new Set(items.map(item => JSON.stringify(item)))).map(item => JSON.parse(item));

        
        newItems.forEach(item => {
            console.log(`Item: ${item.itemName}, Price: ${item.itemPrice}`);
            res.write(`Item: ${item.itemName}, Price: ${item.itemPrice}\n`); 
        });

        console.log(`Captured ${items.length} unique items so far...`);

        
        currentHeight = await page.evaluate('document.body.scrollHeight');
        console.log(`Last height: ${lastHeight}, New height: ${currentHeight}`);

       
        if (currentHeight === lastHeight && items.length === itemsBeforeScroll) {
            scrollAttempts += 1;
            if (scrollAttempts >= 3) {
                console.log("No more new items detected. Stopping scroll.");
                break;
            }
        } else {
            scrollAttempts = 0;  // Reset scroll attempts if new content is found
        }

        itemsBeforeScroll = items.length;  // Update the number of items before the next scroll
        lastHeight = currentHeight;  // Update lastHeight for the next loop
    }

    return items;
}

// Function to capture menu items from the page
async function getMenuItems(page) {
    console.log("Capturing menu items...");
    const items = await page.evaluate(() => {
        const itemElements = document.querySelectorAll('div[aria-label]'); 
        return Array.from(itemElements).map(item => {
            const ariaLabel = item.getAttribute('aria-label') || '';
            const [itemName, itemPrice] = ariaLabel.split(' $');
            return {
                itemName: itemName ? itemName.trim() : 'No Name',
                itemPrice: itemPrice ? `$${itemPrice.trim()}` : 'No Price',
            };
        });
    });

    return items;
}

app.get('/api/prices', async (req, res) => {
    if (isScraping) {
        return res.status(429).send('Scraper is busy, please try again later.');
    }

    isScraping = true;  // Prevent multiple concurrent scrapers
    let browser;
    let items = [];  // Store items incrementally

    try {
        console.log("Launching Puppeteer...");
        browser = await puppeteer.launch({ headless: false, slowMo: 50 });  
        const page = await browser.newPage();

        // DoorDash store URL
        const mainUrl = 'https://www.doordash.com/search/store/pizza';
        await page.goto(mainUrl, { waitUntil: 'domcontentloaded' });
        console.log("Navigated to the search page.");

        // Wait for the store link or menu link to appear
        console.log("Waiting for the store link...");
        await page.waitForSelector('a[href*="/store/2297368"]', { timeout: 60000 });  
        console.log("Store link found. Clicking on the store to load menu...");

        // Click the store link to load the menu
        await page.click('a[href*="/store/2297368"]');  
        await page.waitForNavigation({ waitUntil: 'networkidle2' });  // Wait for the new page (menu) to load

        // Wait for the menu items to load
        console.log("Waiting for menu items to load...");
        await page.waitForSelector('div[aria-label]', { timeout: 90000 });  // target aria-label
        console.log("Menu items loaded.");

        
        res.writeHead(200, { 'Content-Type': 'text/plain; charset=utf-8' });

        
        console.log("Starting to scroll and capture menu items...");
        await autoScrollAndCapture(page, items, res);  // Scroll and capture incrementally

        console.log(`Final captured items count: ${items.length}`);
        res.end("\nFinished scraping.\n");

        await browser.close();  // Close browser after scraping
        console.log("Browser closed.");
    } catch (error) {
        console.error("Error during Puppeteer operation:", error);
        res.status(500).send("Error occurred while scraping data.");
        if (browser) await browser.close();  // Ensure browser is closed on error
    } finally {
        isScraping = false;  // lock
    }
});

app.listen(PORT, () => {
    console.log(`Server is running on http://localhost:${PORT}`);
});
