package kz.greetgo.sandbox.db.register_impl;

import kz.greetgo.depinject.core.Bean;
import kz.greetgo.sandbox.controller.model.ClientDetails;
import kz.greetgo.sandbox.controller.model.ClientListRequest;
import kz.greetgo.sandbox.controller.model.ClientRecord;
import kz.greetgo.sandbox.controller.model.ClientToSave;
import kz.greetgo.sandbox.controller.register.ClientRegister;

import java.util.List;

@Bean
public class ClientRegisterImpl implements ClientRegister {
  @Override
  public long getSize(ClientListRequest clientListRequest) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<ClientRecord> getList(ClientListRequest clientListRequest) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ClientDetails getClient(String id) {
    throw new UnsupportedOperationException();
  }

  @Override
  public ClientRecord saveClient(ClientToSave clientToSave) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void deleteClient(String id) {
    throw new UnsupportedOperationException();
  }
}