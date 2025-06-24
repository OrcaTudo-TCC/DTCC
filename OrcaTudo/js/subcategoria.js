const endpointSubcategoria = "http://localhost:8080/subcategoria"

async function getSubcategoria (categoria) {
    if (typeof categoria !== 'string') {
        throw new Error("O parâmetro precisa ser um texto (string).");
      }
    try{
        const response = await fetch (endpointSubcategoria +"/"+categoria,{
            method:"GET" ,
            headers: { "Content-Type": "application/json" }
        })

        if (response.status === 404){
          console.log('erro na pesquisa: ', response.status)
        }

        const data = await response.json()

        return data
    } catch (error) {
        console.log ("Erro na requisição getSubcategoria: "+ error)

    }
 }

async function postSubcategoria (categoria,nome) {
    try {
        const response = await fetch (endpointSubcategoria, {
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
      const response = await fetch(endpointSubcategoria + "/" + id + "/nome", {
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

  async function putSubcategorianome (id, categoria) {
    try {
      const response = await fetch(endpointSubcategoria + "/" + id + "/categoria", {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(categoria)
      });

      if (!response.ok) {
        throw new Error(response.status);
      }

      const data = await response.json();
      return data;

    } catch (error) {
      console.log ("Erro na Requisição putSubcategoria: " + error);
    }

    }

    async function deleteSubcategoria (id) {
      try{
        const response = await fetch(endpointSubcategoria + "/" + id, {
          method: "DELETE",
          headers: { "Content-Type": "application/json" },
        });

        if (!response.ok) {
          throw new Error(response.status);
        }

        return true;

      } catch (error) {
        console.log ("Erro na Requisição deleteSubcategoria: " + error);
      }

    }

    