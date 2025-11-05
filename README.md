# AwesomeAPI-CurrencyQuotation-AsyncHTTPConnection-JAVA

![Java](https://img.shields.io/badge/Java-11%2B-007396?style=flat-square&logo=java)
![Maven](https://img.shields.io/badge/Maven-Apache-C71A36?style=flat-square&logo=apachemaven)
![AwesomeAPI](https://img.shields.io/badge/API-AwesomeAPI-1E88E5?style=flat-square)

## About This Project

This is a simple Java project built to demonstrate how to make asynchronous HTTP requests using Java 11's native `HttpClient`.

It connects to the [AwesomeAPI](https://docs.awesomeapi.com.br/) to fetch currency exchange rates (BRL-USD, EUR-BRL, BTC-BRL), securely loads an API key using `java-dotenv`, and parses the JSON response using `org.json`.

### A Study Step

**This repository is a foundational study, not a final product.**

The code here is a proof-of-concept for handling async web requests. The logic from `Main.java` is intended to be refactored into a dedicated HTTP connection class for a larger project: **a Java-based currency converter application.**

---

### How to Run (Required Steps)

#### 1. Set Up Your API Key

This project requires a free API token from AwesomeAPI.

1.  **Get your token:** Go to [AwesomeAPI (Token)](https://docs.awesomeapi.com.br/api-de-moedas#token) to get one.
2.  **Create your .env file:** In the project's root directory, or just copy the example file.
    ```bash
    cp .env.example .env
    ```
3.  **Edit the file:** Open the new `.env` file and replace `PLACE_YOUR_AWESOME_API_TOKEN_HERE` with your actual API token.
    ```ini
    # .env
    YOUR_API_TOKEN=89d55164e6e507bab2a51e4684d3421285d611db...
    ```
    The `.gitignore` file is already configured to keep your `.env` file private.

#### 2. Run the Application

You can just run it normally with the "Run" or use the command line below

```bash
mvn exec:java"
```
Maven will automatically compile the code, download dependencies, and run the Main.java file.

---

## Example Output
```bash
  Dispatching request to: https://economia.awesomeapi.com.br/last/BRL-USD,EUR-BRL,BTC-BRL?token=PLACE_YOUR_AWESOME_API_TOKEN_HERE
...request sent. Main thread still free.

[CALLBACK] Response received!
[CALLBACK] Status Code: 200
[CALLBACK] Parsed JSON content:
{
    "BRLUSD": {
        "code": "BRL",
        "codein": "USD",
        "name": "Real Brasileiro/Dólar Americano",
        "high": "0.1741",
        "low": "0.174",
        "varBid": "0",
        "pctChange": "0.01",
        "bid": "0.174",
        "ask": "0.1741",
        "timestamp": "1730412199",
        "create_date": "2024-10-31 08:23:19"
    },
    "EURBRL": {
        "code": "EUR",
        "codein": "BRL",
        "name": "Euro/Real Brasileiro",
        "high": "5.827",
        "low": "5.821",
        "varBid": "0.003",
        "pctChange": "0.05",
        "bid": "5.823",
        "ask": "5.825",
        "timestamp": "1730412200",
        "create_date": "2024-10-31 08:23:20"
    },
    "BTCBRL": {
        "code": "BTC",
        "codein": "BRL",
        "name": "Bitcoin/Real Brasileiro",
        "high": "336829",
        "low": "330000",
        "varBid": "-4857",
        "pctChange": "-1.44",
        "bid": "331144",
        "ask": "331600",
        "timestamp": "1730412204",
        "create_date": "2024-10-31 08:23:24"
    }
}

Finalized, finishing Main thread.
```

**I have a ruleset that requires a pull request before merging into the main branch, create your own branch and add your features, if you have corrections or improvements, please make the request, or DM me, I will gladly accept it**
