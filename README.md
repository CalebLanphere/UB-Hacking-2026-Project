## Inspiration
When talking to ACV Auctions at the career fair, they told us that a main concern they have with their auction site is having web scrapers re-upload their listings elsewhere. This made us start brainstorming ways to help prevent that. This project is more relevant than ever today with the rise of AI agents that can scrape websites very quickly. 

## What it does
Our library takes a website's data and runs it through a randomly generated character mapping, which scrambles the letters of the alphabet. It then writes this obfuscated data to the HTML file, and generates a custom font so that the data is readable for humans on the website, but appears as jargon to any bots scraping the HTML. This is created such that there is no impact on user experience, but it ruins the data for the majority of AI agents, which rely on the website's data.

## How we built it
The demonstration website was written in Java with Springboot, and our font generation is done in Python. The python code is executed on our server using Graalpy. For our demonstration of it's effectiveness, we are using Crawl4AI, to scrape the site. 

## Challenges we ran into
Finding out how to modify fonts was a big challenge, because there is a big lack of information online regarding this topic.

## Accomplishments that we're proud of
Creating custom font files to unscramble our obfuscated HTML.

## What we learned
We learned about how multiple programming languages can work together, and gained more experience working with Springboot and Github.

## What's next for Scrambl
Allow web servers to continuously scramble data in real time.
Find more creative solutions to handle other scrape-able data like photos and ensure the service is scalable.
