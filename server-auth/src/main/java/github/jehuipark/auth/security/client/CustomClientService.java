package github.jehuipark.auth.security.client;

import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientAlreadyExistsException;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

/**
 * Created by JH on 2019-06-06.
 */
@Primary
@Service("clientService")
@RequiredArgsConstructor
public class CustomClientService implements ClientDetailsService, ClientRegistrationService,
    ClientServiceAdapter {

  private final ClientRepository clientRepository;
  private final PasswordEncoder passwordEncoder;

  @Override
  public ClientDetails loadClientByClientId(String clientId) throws NoSuchClientException {

    Client client = clientRepository
        .findById(clientId)
        .orElseThrow(()-> new NoSuchClientException("No client with requested id: " + clientId));
    return clientToClientDetails(client, passwordEncoder);
  }

  @Override
  public void addClientDetails(ClientDetails clientDetails) throws ClientAlreadyExistsException {
    throw new ClientRegistrationException("not implementation");
  }

  @Override
  public void updateClientDetails(ClientDetails clientDetails) throws NoSuchClientException {
    throw new ClientRegistrationException("not implementation");
  }

  @Override
  public void updateClientSecret(String clientId, String secret) throws NoSuchClientException {
    Client client = clientRepository
        .findById(clientId)
        .orElseThrow(()-> new NoSuchClientException("No client found with id = " + clientId));
    client.setClientSecret(passwordEncoder.encode(secret));
    clientRepository.saveAndFlush(client);
  }

  @Override
  public void removeClientDetails(String clientId) throws NoSuchClientException {
    Client client = clientRepository
        .findById(clientId)
        .orElseThrow(()-> new NoSuchClientException("No client found with id = " + clientId));
    clientRepository.delete(client);
  }

  @Override
  public List<ClientDetails> listClientDetails() {
    return clientRepository.findAll()
        .stream()
        .map(client -> clientToClientDetails(client, passwordEncoder))
        .collect(Collectors.toList());
  }

}
