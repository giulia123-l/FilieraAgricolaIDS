package com.example.filiera.factory;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.ShareLink;

public class ShareLinkFactory {
    public ShareLink create(Prodotto prodotto, Long creatoDaUserId) {
        ShareLink s = new ShareLink();
        s.setProdotto(prodotto);
        s.setCreatoDaUserId(creatoDaUserId);
        return s;
    }
}
