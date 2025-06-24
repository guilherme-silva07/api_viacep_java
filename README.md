# CEP - Consultor de Endereços via CEP

![Java](https://img.shields.io/badge/Java-11%2B-orange) ![Licença](https://img.shields.io/badge/license-MIT-blue.svg)

## 📝 Descrição

`CEP` é uma aplicação de console desenvolvida em Java como um projeto de estudo para praticar o consumo de APIs externas. O programa permite que o usuário busque informações detalhadas de um endereço a partir de um Código de Endereçamento Postal (CEP), utilizando a API pública **ViaCEP**.

Além da busca, a aplicação armazena um histórico das consultas em um arquivo local, permitindo ao usuário listar ou limpar esses registros.

## ✨ Funcionalidades

* **Busca de Endereço por CEP:** Consulta em tempo real a API ViaCEP para obter dados de um endereço.
* **Validação de Entrada:** O sistema verifica se o CEP inserido contém 8 dígitos numéricos antes de fazer a consulta.
* **Histórico de Buscas:** Cada consulta bem-sucedida é salva automaticamente em um arquivo `enderecos.txt`.
* **Listagem de Endereços:** Permite visualizar no console todos os endereços salvos no histórico.
* **Limpeza de Histórico:** Oferece uma opção para excluir o arquivo de histórico.
* **Menu Interativo:** Uma interface de linha de comando simples guia o usuário pelas funcionalidades.

## 🛠️ Tecnologias Utilizadas

* **[Java](https://www.java.com/pt_BR/)**: Linguagem de programação utilizada para construir a aplicação.
* **[Gson](https://github.com/google/gson)**: Biblioteca do Google para serializar e desserializar objetos Java para o formato JSON.
* **[ViaCEP API](https://viacep.com.br/)**: API gratuita e pública para consulta de CEPs no Brasil.