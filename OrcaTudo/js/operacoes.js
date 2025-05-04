const endpointOperacao = "http://localhost:8080/operacao";

// Retorna todas as operações de um usuário pelo ID
async function getOperacoesByUsuarioId(idUsuario) {
  if (typeof idUsuario === "number" && Number.isInteger(idUsuario)) {
    try {
      const response = await fetch(`${endpointOperacao}/${idUsuario}`, {
        method: "GET",
        headers: { "Content-Type": "application/json" }
      });

      if (!response.ok) {
        if (response.status === 404) {
          console.log("Usuário não encontrado.");
          return null;
        }
        throw new Error("Erro na requisição GET: " + response.status);
      }

      const data = await response.json();
      return data;
    } catch (err) {
      console.log("Erro na requisição get operações: " + err);
      return null;
    }
  } else {
    console.log("ID de usuário inválido.");
    return null;
  }
}

// Cria uma nova operação
async function postOperacao(operacao) {
  const camposObrigatorios = ["idUsuario", "operacao", "status"];

  if (typeof operacao !== "object" || operacao === null) {
    throw new Error("Parâmetro inválido: não é um objeto.");
  }

  const todosPresentes = camposObrigatorios.every(campo => campo in operacao);
  if (!todosPresentes) {
    throw new Error("Corpo da requisição incompleto. Faltando algum campo.");
  }

  try {
    const response = await fetch(endpointOperacao, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        idUsuario: operacao.idUsuario,
        operacao: operacao.operacao,
        status: operacao.status
      })
    });

    if (!response.ok) {
      throw new Error("Erro na requisição POST: " + response.status);
    }

    const data = await response.json();
    return data;
  } catch (err) {
    console.log("Erro na requisição post operação: " + err);
    return null;
  }
}

// Atualiza o status de uma operação
async function putStatusOperacao(idOperacao, novoStatus) {
  if (!(typeof idOperacao === "number" && Number.isInteger(idOperacao))) {
    throw new Error("Id da operação inválido.");
  }

  if (typeof novoStatus !== "string") {
    throw new Error("Status inválido, deve ser uma string.");
  }

  try {
    const response = await fetch(`${endpointOperacao}/${idOperacao}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(novoStatus)
    });

    if (!response.ok) {
      if (response.status === 404) {
        console.log("Operação não encontrada.");
        return null;
      }
      throw new Error("Erro na requisição PUT: " + response.status);
    }

    const data = await response.json();
    return data;
  } catch (err) {
    console.log("Erro na requisição put operação: " + err);
    return null;
  }
}

export {
  getOperacoesByUsuarioId,
  postOperacao,
  putStatusOperacao
};
