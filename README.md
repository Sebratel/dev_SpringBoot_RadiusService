# dev_SpringBoot_RadiusService

Conexão do assinante: consumo, última conexão, autenticação e duplicidades.

Parte da migração do BFF para API Gateway (`Sebratel/dev_SpringBoot_APIGateway`, `specs/API_GATEWAY.md`).
Serviço **radius-service**, porta **8104**, atrás do `dev_SpringBoot_Gateway`.

> **Estado: esqueleto.** Sobe, expõe `/actuator/health` (público; o resto exige JWT) e tem build, Checkstyle e JaCoCo ativos, mas **a lógica ainda está no
> monólito**. As rotas abaixo são o contrato que este serviço precisa atender, idêntico ao do BFF atual (`contract/routes.json`).

## Rotas que este serviço deve atender (14)

| Método | Rota | Acesso |
|---|---|---|
| `GET` | `/api/v1/consumo` | autenticada |
| `GET` | `/api/v1/consumo-alto` | autenticada |
| `GET` | `/api/v1/consumo-alto-paginado` | autenticada |
| `GET` | `/api/v1/contracts/last-connections` | autenticada |
| `GET` | `/api/v1/contracts/ultimas-conexoes` | autenticada |
| `GET` | `/api/v1/contratos/last-connections` | autenticada |
| `GET` | `/api/v1/contratos/ultimas-conexoes` | autenticada |
| `GET` | `/api/v1/high-consumption` | autenticada |
| `GET` | `/api/v1/high-consumption-paged` | autenticada |
| `GET` | `/api/v1/radius/duplicate-calling-stations` | autenticada |
| `GET` | `/api/v1/radius/duplicate-prefixes` | autenticada |
| `GET` | `/api/v1/radius/first-authentications` | autenticada |
| `GET` | `/api/v1/ultima-conexao/last-connections` | autenticada |
| `GET` | `/api/v1/ultima-conexao/ultimas-conexoes` | autenticada |

## Desenvolvimento

O `sebratel-java-parent` vem do GitHub Packages (o Maven exige token `read:packages` até para pacotes públicos). Antes do primeiro build, crie no `~/.m2/settings.xml` um servidor
`github` com um token `read:packages`:

```xml
<settings><servers><server>
  <id>github</id><username>SEU_USUARIO</username><password>SEU_TOKEN</password>
</server></servers></settings>
```

```bash
./mvnw verify          # testes + Checkstyle + JaCoCo (cobertura mínima 0.50, herdada do parent)
./mvnw spring-boot:run
```

Variáveis de ambiente: ver `.env.example` (nunca commite o `.env`).

## Regras do repositório
- `main` só recebe PR, com 1 aprovação e o check `ci-ok` verde. Commits no padrão Conventional Commits.
- Dependências compartilhadas (`common-web`, `common-security`, `common-elleven`) vêm de `dev_Maven_Commons`
  e entram quando houver código a compartilhar.
- Remover ou renomear rota é mudança incompatível: o contrato é o manifesto do monólito (`docs/contract/manifest.json`).

## Deploy em staging (Portainer)

Stack criada **a partir do Git**: repositório `https://github.com/Sebratel/dev_SpringBoot_RadiusService`, branch `staging`,
arquivo `docker-compose-stage.yml`. O container se chama `radius-service-stage` e fica na rede externa `proxy`.
Sem porta publicada no host: só o gateway, na rede `proxy`, alcança este serviço.

A imagem `ghcr.io/sebratel/radius-service:staging` é construída pelo GitHub Actions (`.github/workflows/staging-image.yml`, a cada push em `staging`) a partir
do `Dockerfile` (multi-stage, Temurin 21, usuário não-root) e publicada no GHCR; o Portainer só a baixa. Construir a imagem dentro do host
passa de 90 s, e o proxy do stage derruba a requisição de deploy. O `sebratel-java-parent` é construído da fonte pública na própria imagem, sem token.

O branch `staging` recebe o código mais atualizado: `main` → `staging` antes de cada deploy.
