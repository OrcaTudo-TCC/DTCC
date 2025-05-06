const endpoint = "http://localhost:8080/subcategoria"

async function getSubcategoria (categoria) {
    if (typeof parametro !== 'string') {
        throw new Error("O parâmetro precisa ser um texto (string).");
      }
    try{
        const response = await fetch (endpoint +"/"+categoria,{
            method:"GET" ,
            headers: { "Content-Type": "application/json" }
        })

        if (!response.ok){
            throw new Error (response.status)
        }

        const data = await response.json()

        return data
    } catch (error) {
        console.log ("Erro na requisição getSubcategoria: "+ error)

    }
 }

async function postSubcategoria (categoria,nome) {
    try {
        const response = await fetch (endpoint, {
            method:"POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                
                "nome":nome,
                "nomeDaCategoria":categoria
            })})

    if (!response.ok){
    throw new Error (response.status)
 }
   const data = await response.json()
                 
    return data 
}   catch (error) {
    console.log ("Erro na Requisição postSubcategoria: "+ error)
 }
}

async function putSubcategorianome(id, nome) {
    try {
      const response = await fetch(endpoint + "/" + id + "/nome", {
        method: "PUT",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify(nome)
      });
  
      if (!response.ok) {
        throw new Error(response.status);
      }
  
      const data = await response.json();
      return data;
  
    } catch (error) {
      console.log("Erro na Requisição putSubcategoria: " + error);
    }
  }
  
    


