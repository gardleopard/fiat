package com.netflix.spinnaker.fiat.controllers;

import com.netflix.spinnaker.fiat.model.resources.ServiceAccount;
import com.netflix.spinnaker.fiat.providers.ResourceProvider;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping(path = "/serviceAccounts")
public class ServiceAccountsController {
  private final ResourceProvider<ServiceAccount> serviceAccountResourceProvider;

  public ServiceAccountsController(ResourceProvider<ServiceAccount> serviceAccountResourceProvider) {
    this.serviceAccountResourceProvider = serviceAccountResourceProvider;
  }
  @RequestMapping(value = "/{serviceAccountName:.+}", method = RequestMethod.PUT)
  public Map<String, String> updateApplication(@PathVariable String serviceAccountName, @RequestBody @NonNull List<String> memberOf, HttpServletResponse response) {
    ServiceAccount serviceAccount = new ServiceAccount();
    serviceAccount.setName(serviceAccountName);
    serviceAccount.setMemberOf(memberOf);
    log.info("Updating serviceAccount {}", serviceAccountName);

    serviceAccountResourceProvider.addItem(serviceAccount);
    return Collections.singletonMap("status", "success");
  }
}
