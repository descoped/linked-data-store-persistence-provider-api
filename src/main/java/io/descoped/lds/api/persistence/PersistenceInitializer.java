package io.descoped.lds.api.persistence;

import io.descoped.lds.api.persistence.reactivex.RxJsonPersistence;

import java.util.Map;
import java.util.Set;

public interface PersistenceInitializer {

    String persistenceProviderId();

    Set<String> configurationKeys();

    RxJsonPersistence initialize(String defaultNamespace, Map<String, String> configuration, Set<String> managedDomains);
}
