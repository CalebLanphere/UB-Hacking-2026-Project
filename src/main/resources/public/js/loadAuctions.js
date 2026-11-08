const initLoadAuctionVars = () => {
    const list = document.getElementById("auction-list")
    updateList()
}

const getSpecificAuction = () => {
    const list = document.getElementById("auction-list")

    fetch("/api/scramble/auction?bidID=10").then(res => {
        console.log("submitted")
        if(res.status === 200) {
            res.json().then(json => {
                let div = document.createElement('div')
                let bidId = document.createElement('P')
                bidId.innerText = json.bidId
                let itemName = document.createElement('P')
                itemName.innerText = json.itemName
                let itemDesc = document.createElement('P')
                itemDesc.innerText = json.itemDesc
                let sellerName = document.createElement('P')
                sellerName.innerText = json.sellerName

                console.log(json)

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

const getAuctions = () => {
    const list = document.getElementById("auction-list")

    fetch("/api/scramble/auction").then(res => {
        console.log("submitted")
        if(res.status === 200) {
            res.json().then(json => {
                for (let i = 0; i < json.length; i++) {
                    console.log(json[i])
                    let div = document.createElement('div')
                    let bidId = document.createElement('P')
                    bidId.innerText = json[i].bidId
                    let itemName = document.createElement('P')
                    itemName.innerText = json[i].itemName
                    let itemDesc = document.createElement('P')
                    itemDesc.innerText = json[i].itemDesc
                    let sellerName = document.createElement('P')
                    sellerName.innerText = json[i].sellerName

                    div.appendChild(bidId)
                    div.appendChild(itemName)
                    div.appendChild(itemDesc)
                    div.appendChild(sellerName)

                    list.appendChild(div)
                }
            })
        }
        if(res.status === 500 || res.status === 400) {

        }
    })
}

document.addEventListener("DOMContentLoaded", getAuctions)