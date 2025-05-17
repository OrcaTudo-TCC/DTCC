const endpoint = "http://localhost:8080/auth/login";

async function login(email, password) {
  if (!email || !password) {
    throw new Error("Email e senha são obrigatórios.");
  }

  try {
    const response = await fetch(endpoint, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        email: email,
        password: password
      })
    });

    if (!response.ok) {
      if (response.status === 404) {
        console.log("Usuário não encontrado.");
        return null;
      }
      throw new Error("Erro no login: " + response.status);
    }

    const data = await response.json();
    return data;

  } catch (err) {
    console.log("Erro na requisição de login: " + err.message);
    return null;
  }
}
