const endpointSubFinal = "http://localhost:8080/subcategoriafinal"

async function getSubcategoriafinal (subcategoria) {
    try{
      const response = await fetch(endpointSubFinal + "/" + subcategoria, {
        method: "GET",
        headers: { "Content-Type": "application/json" }
        
      });

      if (!response.ok) {
        throw new Error(response.status);
      }
      const data = await response.json();
      return data;

    } catch (error) {
      console.log ("Erro na Requisição getSubcategoriafinal: " + error);
      return null;
    }
  }


  async function postSubcategoriafinal (subcategoriafinal , nome) {
    try{
      const response = await fetch(endpointSubFinal,{
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify ({

          "nome": nome,
          "nomeDasubcategoriafinal": subcategoriafinal

        })});

        if (response.status == 404){
          console.log('erro na pesquisa: ', response.status)
        }
        const data = await response.json();
        return data;

    } catch (error) {
      console.log ("Erro na Requisição postSubcategoriafinal: " + error);
    }
  }


  async function putSubcategoriafinal (id, nome) {
    try {
      const response = await fetch(endpointSubFinal + "/" + id + "/nome",{
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
      const response = await fetch(endpointSubFinal + "/" + id, {
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
      const response = await fetch(endpointSubFinal + "/" + idSubcategoriaFinal + "/update_subcategoria", {
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

  
  

  


  


