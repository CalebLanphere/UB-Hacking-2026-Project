const initLoadAuctionVars = () => {
    const list = document.getElementById("auction-list")
    updateList()
}

const updateList = () => {
    const list = document.getElementById("auction-list")

    fetch("/api/scramble/auction?bidID=10").then(res => {
        console.log("submitted")
        if(res.status === 200) {
            res.json().then(json => {
                let div = document.createElement('div')
                let bidId = document.createElement('P')
                bidId.innerText = json.bidId.string
                let itemName = document.createElement('P')
                itemName.innerText = json.itemName.string
                let itemDesc = document.createElement('P')
                itemDesc.innerText = json.itemDesc.string
                let sellerName = document.createElement('P')
                sellerName.innerText = json.sellerName.string

                div.appendChild(bidId)
                div.appendChild(itemName)
                div.appendChild(itemDesc)
                div.appendChild(sellerName)

                list.appendChild(div)
            })
        }
        if(res.status === 500 || res.status === 400) {

        }
    })
}

document.addEventListener("DOMContentLoaded", initLoadAuctionVars)