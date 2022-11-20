const cookieArr = document.cookie.split("=")
const userId = cookieArr[1];
console.log(userId)
const submitForm = document.getElementById("champions-form")
const championsContainer = document.getElementById("champions-container")

let championsBody = document.getElementById(`champions-body`)
let updatechampionsBtn = document.getElementById(`update-champions-button`)

const headers = {
'Content-Type':'application/json'}


const baseUrl = "http://localhost:8080/api/v1/champions/"

function handleLogout() {
let c = document.cookie.split(";");
for(let i in c ) {
document.cookie = /^[^=]+/.exec(c[i])[0]+"=;expires=Thu,01 Jan 1970 00:00:00 GMT"
}
}

const handleSubmit = async (e) => {
e.preventDefault()
let bodyObj = {
body:   document.getElementById("champions-input").value
}
await addchampions(bodyObj);
document.getElementById("champions-input").value = ''
}

async function addchampion(obj) {
const response = await fetch(`${baseUrl}user/${userId}`,{
method:"POST",
body:JSON.stringify(obj),
headers:headers
})

.catch(err=> console.error(err.message))
if(response.status == 200) {
return getchampions(userId);
}
}

async function getchampions(userId) {
await fetch (`${baseUrl}user/${userId}`,{
method:"GET",
headers:headers
})
.then(response => response.json())
.then(data => createchampionsCards(data))
.catch(err => console.error(err))
}

async function getchampionsById(championsId) {
await fetch(baseUrl + championsId, {
method:"GET",
headers:headers
})
.then(res =>res.json())
.then(data => populateModal(data))
.catch(err => console.error(err.message))
}

async function handlechampionsEdit(championsId) {
let bodyObj = {
id: championsId,
body: championsBody.value
}

await fetch(baseUrl, {
method:"Put",
body:JSON.stringify(bodyObj),
headers:headers
})

.catch(err => console.error(err))

return  getchampions(userId);
}

async function handleDelete(championsId) {
await fetch (baseUrl +championsId, {
method:"DELETE",
headers:headers
})
.catch(err => console.error(err))
return getchampions(userId);
}

const createchampionsCards = (array) => {
    championsContainer.innerHTML = ''
    array.forEach(obj => {
        let championsCard = document.createElement("div")
        championsCard.classList.add("m-2")
        championsCard.innerHTML = `
            <div class="card d-flex" style="width: 18rem; height: 18rem;">
                <div class="card-body d-flex flex-column  justify-content-between" style="height: available">
                    <p class="card-text">${obj.body}</p>
                    <div class="d-flex justify-content-between">
                        <button class="btn btn-danger" onclick="handleDelete(${obj.id})">Delete</button>
                        <button onclick="getchampionsById(${obj.id})" type="button" class="btn btn-primary"
                        data-bs-toggle="modal" data-bs-target="#champion-edit-modal">
                        Edit
                        </button>
                    </div>
                </div>
            </div>
        `
        championsContainer.append(championsCard);
    })
}

const populateModal = (obj) =>{
championsBody.innerText=''
championsBody.innerText= obj.body
updatechampionsBtn.setAttribute('data-champions-id',obj.id)
}

getchampions(userId);

if(submitForm)
{submitForm.addEventListener("submit",handleSubmit)}

if(updatechampionsBtn){
updatechampionsBtn.addEventListener("click",(e) =>{
let championsId = e.target.getAttribute('data-champions-id')
handlechampionsEdit(championsId);
})}
//<a class="btn btn-danger navbar-btn" href="./login.html" onclick="handleLogout()">Logout</a>