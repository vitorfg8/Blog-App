# Blog App

O Blog App é uma aplicativo que permite aos usuários listar e criar postagens. O aplicativo se comunica com uma API REST simples, desenvolvida com [Ktor](https://ktor.io/), com o objetivo de apenas simular um serviço web

<img
  src="APP/screenshots/Screenshot_20240601_094443.png"
  alt="Tela inicial"
  width="260" /> <img
  src="APP/screenshots/Screenshot_20240601_094513.png"
  alt="Tela de detalhes da postagem"
  width="260" />  <img
  src="APP/screenshots/Screenshot_20240601_094535.png"
  alt="Tela de criação de postagem"
  width="260" />


## Execução do Projeto

### Preparação do Ambiente

1. Faça o clone do repositório do projeto.
2. Baixe e instale o [Android Studio](https://developer.android.com/studio/install).
3. Baixe e instale o [IntelliJ IDEA](https://www.jetbrains.com/help/idea/installation-guide.html) ou outra IDE de sua preferência.

### Execução da API com o IntelliJ IDEA

1. Abra o IntelliJ IDEA.
2. Importe o projeto dentro da pasta API.
3. Localize o arquivo Application.kt dentro do projeto.
4. Execute a aplicação clicando no botão "Run" na função main"
5. Aguarde até que a API esteja em execução.

### Execução do Aplicativo com o Android Studio

1. Abra o Android Studio.
2. Importe o projeto dentro da para APP.
3. Execute o aplicativo clicando no botão "Run"
4. Aguarde até que o aplicativo seja instalado e iniciado no dispositivo ou emulador.

## Arquitetura

O app foi desenvolvido seguindo os padrões da arquitetura MVVM e da Clean Architecture, promovendo uma clara separação de responsabilidades e facilitando a manutenção do código. No MVVM, o Model gerencia os dados e a lógica de negócios, o View é responsável pela interface do usuário e o ViewModel atua como intermediário entre eles. Isso permite o desenvolvimento independente de cada componente, resultando em código mais organizado e reutilizável. Além disso,a Clean Architecture organiza o código em camadas bem definidas (Domain, Data e Presentation/UI), promovendo a independência entre elas e melhorando a escalabilidade do aplicativo.

Foi adotado o padrão de injeção de dependências para desacoplar classes e tornar o código mais modular e testável. O Koin foi escolhido devido à sua simplicidade, integração com Kotlin e eficiência, facilitando o desenvolvimento e manutenção do código.

Para lidar com operações assíncronas, foi utilizado Flow e Coroutines. Isso simplifica o tratamento de chamadas assíncronas, resultando em código mais limpo, fácil de manter e com melhor performance, sem a necessidade de bibliotecas externas.

Na interface do usuário, foi utlizado o Jetpack Compose, um toolkit moderno que simplifica a construção de interfaces, permitindo criar telas com menos código e maior praticidade, utilizando a mesma linguagem do aplicativo, o Kotlin.