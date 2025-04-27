class Usuario {
    constructor(id_usuario, email, cpf_cnpj, telefone, endereco, senha, nome, role_id) {
      this.id_usuario = id_usuario;
      this.email = email;
      this.cpf_cnpj = cpf_cnpj;
      this.telefone = telefone;
      this.endereco = endereco;
      this.senha = senha;
      this.nome = nome;
      this.role_id = role_id;
    }

      //  Métodos (Funções do usuario )

    exibirInformacoes() {
        console.log(`Usuário: ${this.nome}`);
        console.log(`Email: ${this.email}`);
        console.log(`CPF/CNPJ: ${this.cpf_cnpj}`);
        console.log(`Telefone: ${this.telefone}`);
        console.log(`Endereço: ${this.endereco}`);
        console.log(`Tipo de usuário (Role ID): ${this.role_id}`);
    }

}


