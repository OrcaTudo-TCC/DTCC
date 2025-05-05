const endpoint = "http://localhost:8080/categoria"

async function getCategoria () {
    try {
        const response = await fetch (endpoint,{
            method:"GET",
            headers: { "Content-Type": "application/json" }
        })

        if(!response.ok){
            throw new Error( response.status)
        }

        const data = await response.json()

        return data 
    } catch (error) {
        console.log ("Erro na Requisição getCategoria: "+ error)
    }
}

async function postCategoria (nome) {
    try {
        const response = await fetch (endpoint + "/"+ nome,{
            method:"POST",
            headers: { "Content-Type": "application/json" },

        })

        if(!response.ok){
            throw new Error( response.status)
        }

        const data = await response.json()

        return data 
    } catch (error) {
        console.log ("Erro na Requisição postCategoria: "+ error)
    }
}

async function putCategoria (nome,idcategoria) {
    try {
        const response = await fetch (endpoint + "/"+ idcategoria,{
            method:"PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                nome
            })

        })

        if(!response.ok){
            throw new Error( response.status)
        }

        const data = await response.json()

        return data 
    } catch (error) {
        console.log ("Erro na Requisição putCategoria: "+ error)
    }
}

async function deleteCategoria (idcategoria) {
    try {
        const response = await fetch (endpoint + "/"+ idcategoria,{
            method:"DELETE",
            headers: { "Content-Type": "application/json" },

        })

        if(!response.ok){
            throw new Error( response.status)
        }

        const data = await response.json()

        return data 
    } catch (error) {
        console.log ("Erro na Requisição deleteCategoria: "+ error)
    }
}