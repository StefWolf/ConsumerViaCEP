package com.cep.cep.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import org.springframework.stereotype.Service;

import com.cep.cep.model.Endereco;
import com.google.gson.Gson;

@Service
public class CepService {

	public Endereco findEnderecoByCEP(String cep) throws IOException, InterruptedException {
		
		String Url = "http://viacep.com.br/ws/" + cep + "/json";
		URI endereco = URI.create(Url);
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
		
		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
		String enderecoJsonEmString = response.body();
		
		return new Gson().fromJson(enderecoJsonEmString, Endereco.class);
		
	}
}
