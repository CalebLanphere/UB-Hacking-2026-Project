const initLoadAuctionVars = () => {
    updateList()
}

const createAuctionRow = (auction) => {
    const row = document.createElement('div')
    row.style.display = 'flex'
    row.style.flexDirection = 'row'
    row.style.alignItems = 'center'
    row.style.gap = '24px'
    row.style.padding = '8px 0'
    row.style.borderBottom = '1px solid #ccc'

    const fields = [auction.bidId, auction.itemName, auction.itemDesc, auction.sellerName]
    for (const value of fields) {
        const p = document.createElement('p')
        p.innerText = value
        p.style.margin = '0'
        row.appendChild(p)
    }

    return row
}

const getSpecificAuction = () => {
    const list = document.getElementById("auction-list")

    fetch("/api/scramble/auction?bidID=10").then(res => {
        console.log("submitted")
        if (res.status === 200) {
            res.json().then(json => {
                console.log(json)
                list.appendChild(createAuctionRow(json))
            })
        }
        if (res.status === 500 || res.status === 400) {

        }
    })
}

const getAuctions = () => {
    const list = document.getElementById("auction-list")

    fetch("/api/scramble/auction").then(res => {
        console.log("submitted")
        if (res.status === 200) {
            res.json().then(json => {
                for (let i = 0; i < json.length; i++) {
                    console.log(json[i])
                    list.appendChild(createAuctionRow(json[i]))
                }
            })
        }
        if (res.status === 500 || res.status === 400) {

        }
    })
}

document.addEventListener("DOMContentLoaded", getAuctions)