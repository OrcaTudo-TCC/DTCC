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

  async function putSubcategorianome (id, categoria) {
    try {
      const response = await fetch(endpoint + "/" + id + "/categoria", {
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
        const response = await fetch(endpoint + "/" + id, {
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

    async function getSubcategoriafinal (subcategoria) {
      try{
        const response = await fetch(endpoint + "/" + subcategoria, {
          method: "GET",
          headers: { "Content-Type": "application/json" },
          
        });

        if (!response.ok) {
          throw new Error(response.status);
        }
        const data = await response.json();
        return data;

      } catch (error) {
        console.log ("Erro na Requisição getSubcategoriafinal: " + error);
      }
    }

    async function postSubcategoriafinal () {
      try{
        const response = await fetch(endpoint,{
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify ({

            "nome": nome,
            "nomeDasubcategoriafinal": subcategoriafinal

          })});

          if (!response.ok) {
            throw new Error(response.status);
          }
          const data = await response.json();
          return data;

      } catch (error) {
        console.log ("Erro na Requisição postSubcategoriafinal: " + error);
      }
    }

    async function putSubcategoriafinal (id, nome) {
      try {
        const response = await fetch(endpoint + "/" + id + "/nome",{
          method:"PUT",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify ({nome:nome})
        });

        if (!response.ok) {
          throw new Error(response.status);
        }
        const data = await response.json();
        return data;

      } catch (error) {
        console.log ("Erro na Requisição putSubcategoriafinal: " + error);
      }

    }

    async function deleteSubcategoriafinal (id) {
      try{
        const response = await fetch(endpoint + "/" + id, {
          method:"DELETE",
          headers: { "Content-Type": "application/json" },         
        });

        if (!response.ok) {
          throw new Error(response.status);
        }
      
        return true;

      }  catch (error) {
        console.log ("Erro na Requisição deleteSubcategoria: " + error);

      }
    }

    async function putSubcategoriafinal (idSubcategoria, idSubcategoriaFinal) {
      if(typeof idSubcategoria !== 'number' || !Number.isInteger(idSubcategoria)){
        throw new Error("O id da subcategoria não é um inteiro");
      }
      if(typeof idSubcategoriaFinal !== 'number' || !Number.isInteger(idSubcategoriaFinal)){
        throw new Error("O id da subcategoriafinal não é um inteiro");
      }
      try{
        const response = await fetch(endpoint + "/" + idSubcategoriaFinal + "/update_subcategoria", {
          method: "PUT",
          headers: { "Content-Type": "application/json" },         
          body:JSON.stringify (idSubcategoria)
        });

        if (!response.ok){
          throw new Error(response.status);
        }

        return true;

      } catch (error){
        console.log ("Erro na Requisição putSubcategoriafinal: " + error);
      }
    }

    
    

    
  
  
    


