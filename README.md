# Scrambl - Data Obfuscation targeted to disrupt AI Agent web scrapers

### Created for the UBHacking 2026 Hackathon

Caleb Lanphere & Will Jones

## Purpose
Increase the difficulty of extracting data from a source without unduly impacting end users

## History
This project came about from a discussion with a e-commerce leader ACV Auctions and there difficulties protecting their data from AI Agents scraping the information from their pages and reposting them externally, potentially causing reputational damage.

This was a problem we could see growing prevalent everywhere, especially with the speed that AI Agents are able to scrape the entire web for any data and modify it how it deems fit. This is the problem we set out to solve.

## Methodology
We had two main requirements for our system:
1. The system must not further impact users. The reason other mitigation techniques were not able to be used was due to the undue impact it would have on users. For this reason, we must not have any impact on end users
2. The system must not let AI Agents read legible data.

With these two guiding factors, we set out with our solution. We wanted to be able to secretly encode and decode text provided by the server to the client without clearly exposing the method to which we decode the text. This led us to our solution.

## Solution
Fonts are a set of mappings that set specific Unicode characters to a specific glyph. Since these contained a mapping of valid character data, we could use them to decode our scrambled data. Fonts are only used for GUI elements, so all AI Agents that scrape sites ignore this element, leaving the scrambled data for them to access.

This solution used a randomly generated mapping of Unicode characters to randomly map characters to glyphs in our modified fonts. By doing the same method in reverse, we could generate a mapping of characters to encode text with that would visually return to the original text. This solution achieves both requirements, as the AI Agents would only see garbled data and the user would see the expected text without compromise to their user experience.

## Features
 - Easy Implementation: Our system is designed to require no modification to external data you want to scramble. This means that using this library is as simple as calling a method.
 - Universal For All Fonts: Our system is font-agnostic, so you can provide any font you wish to create an obfuscation for and it will create mappings for the characters inside that font.
 - Scalable: Our system was created with scalability in mind! This software only needs persistent data storage for the mappings generated. The system loads this data from the database when the first call to scramble is initiated and will not request it again.

## Trade Offs
1. It requires the use of our modified font for any data you are obfuscating. This can ruin non-scrambled text if this font is used in the incorrect spots in the front end.
2. Copy-Paste will have to be handled in Javascript at runtime, which can expose the data obfuscation technique
3. Screen Readers will be impacted, as it will not have legible data to use

## Further Information
Additional Information can be found in the Product Pitch at: https://youtu.be/dXrAe5yPwdI
