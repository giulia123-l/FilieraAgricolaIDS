package com.example.filiera.security;

import java.util.EnumMap;
import java.util.Map;

public class PermissionService {

    private final Map<Action, PermissionStrategy> strategies = new EnumMap<>(Action.class);

    public PermissionService() {
        strategies.put(Action.CREATE_MARKETPLACE, new CreateMarketplacePermission());
        strategies.put(Action.ADD_LISTING, new AddListingPermission());
        strategies.put(Action.CREATE_ORDER, new CreateOrderPermission());
        strategies.put(Action.CREATE_EVENT, new CreateEventPermission());
    }

    public PermissionService register(Action action, PermissionStrategy strategy) {
        strategies.put(action, strategy);
        return this;
    }

    public PermissionStrategy strategyFor(Action action) {
        var s = strategies.get(action);
        if (s == null) throw new IllegalStateException("Nessuna strategy per " + action);
        return s;
    }
}
