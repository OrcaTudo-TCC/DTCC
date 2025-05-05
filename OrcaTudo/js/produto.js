
const endpoint = "http://localhost:8080/produtos";

/*  Para criar um produto deve-se passar por parêmtro um objeto conforme a seguir:
    {
        "nome": "string",
        "descricao": "string",
        "preco": 0,
        "nomeSubcategoriaFinal": "string",
        "nomeFornecedor": "string",
    }
*/
async function postProduto(produto , imagem) {
    try {
        const camposObrigatorios = [
            "nome",
            "descricao",
            "preco",
            "nomeSubcategoriaFinal",
            "nomeFornecedor"
        ]
        if (typeof produto !== 'object' || produto === null) {
            throw new Error("Parâmetro inválido: não é um objeto.");
        }
        const todosPresentes = camposObrigatorios.every(campo => campo in produto);
        if (!todosPresentes) {
            throw new Error("corpo da requisição incompleto. Faltando algum campo.");
        }
        const formData = FormData();
        formData.append("nome", produto.nome);
        formData.append("descricao", descricao);
        formData.append("preco", preco);
        formData.append("nomeDasubcategoriafinal", idSubcategoriaFinal);
        formData.append("nomeDoFornecedor", idFornecedor);
        formData.append("imagem", imagem);
        //requisição com fecth
        const response = fetch(endpoint,{
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: formData
        })
        if(!response.ok){
            throw new Error("Erro na requisição: "+ response.status);
        }
        const data = (await response).json();

        return data;
    } catch (error) {
        console.log("Erro no método post item: "+ error)
    }
}