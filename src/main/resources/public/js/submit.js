const initVariables = () => {
    const form = document.getElementById("scrambleTextForm")

    form.addEventListener("submit", submitTextToScramble)
}

const submitTextToScramble = (event) => {
    event.preventDefault()
    const form = document.getElementById("scrambleTextForm")

    fetch("/api/scramble/upload-auction", {
        method: "put",
        body: new FormData(form)
    }).then(res => {
        console.log("submitted")
        if(res.status === 201) {
            console.log("created")
        }
        if(res.status === 500 || res.status === 400) {

        }
    })
}

document.addEventListener("DOMContentLoaded", initVariables)