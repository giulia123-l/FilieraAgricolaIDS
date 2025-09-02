package com.example.filiera.service;

import com.example.filiera.domain.Prodotto;
import com.example.filiera.domain.ShareLink;
import com.example.filiera.domain.StatoProdotto;
import com.example.filiera.domain.Utente;
import com.example.filiera.dto.CreateShareLinkRequest;
import com.example.filiera.dto.ShareLinkResponse;
import com.example.filiera.factory.ShareLinkFactory;
import com.example.filiera.repository.ProductRepository;
import com.example.filiera.repository.ShareLinkRepository;
import com.example.filiera.repository.UserRepository;
import com.example.filiera.security.Action;
import com.example.filiera.security.PermissionService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SocialShareService {

    private final ShareLinkRepository shareRepo;
    private final ProductRepository productRepo;
    private final UserRepository userRepo;
    private final PermissionService permissionService = new PermissionService();
    private final ShareLinkFactory factory = new ShareLinkFactory();

    public SocialShareService(ShareLinkRepository shareRepo, ProductRepository productRepo, UserRepository userRepo) {
        this.shareRepo = shareRepo;
        this.productRepo = productRepo;
        this.userRepo = userRepo;
    }

    @Transactional
    public ShareLinkResponse createLink(CreateShareLinkRequest req) {
        Utente u = userRepo.findById(req.getCreatoDaUserId()).orElseThrow(() -> new EntityNotFoundException("Utente non trovato"));
        permissionService.strategyFor(Action.CREATE_SHARE_LINK).check(u);
        Prodotto p = productRepo.findById(req.getProdottoId()).orElseThrow(() -> new EntityNotFoundException("Prodotto non trovato"));
        if (p.getStato() != StatoProdotto.APPROVATO) throw new IllegalStateException("Prodotto non approvato");
        ShareLink link = factory.create(p, u.getId());
        ShareLink saved = shareRepo.save(link);
        return toResp(saved);
    }

    @Transactional
    public void trackClick(String token) {
        ShareLink l = shareRepo.findByToken(token).orElseThrow(() -> new EntityNotFoundException("Link non trovato"));
        l.setClickCount(l.getClickCount() + 1);
        shareRepo.save(l);
    }

    public List<ShareLinkResponse> listByCreator(Long creatoDaUserId) {
        return shareRepo.findByCreatoDaUserId(creatoDaUserId).stream().map(this::toResp).collect(Collectors.toList());
    }

    private ShareLinkResponse toResp(ShareLink s) {
        ShareLinkResponse r = new ShareLinkResponse();
        r.setId(s.getId());
        r.setToken(s.getToken());
        r.setProdottoId(s.getProdotto().getId());
        r.setClickCount(s.getClickCount());
        r.setCreatedAt(s.getCreatedAt());
        return r;
    }
}
