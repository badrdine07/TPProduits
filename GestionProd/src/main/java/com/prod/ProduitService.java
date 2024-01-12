package com.prod;

import java.util.ArrayList;
import java.util.List;

public class ProduitService {

    private List<Produit> produits;

    public ProduitService() {
        this.produits = new ArrayList<>();
    }

    // Opération CREATE (ajouter un produit)
    public void ajouterProduit(Produit nouveauProduit) {
        validerUniciteProduit(nouveauProduit);
        validerDonneesProduit(nouveauProduit);
        produits.add(nouveauProduit);
    }

    // Opération READ (lire tous les produits)
    public List<Produit> obtenirTousLesProduits() {
        return new ArrayList<>(produits);
    }

    // Opération READ (lire un produit par son ID)
    public Produit obtenirProduitParId(Long id) {
        return produits.stream()
                .filter(produit -> produit.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Produit non trouvé avec l'ID : " + id));
    }

    // Opération UPDATE (mettre à jour un produit)
    public void mettreAJourProduit(Produit produitMaj) {
        validerUniciteProduit(produitMaj);
        validerDonneesProduit(produitMaj);

        int index = produits.indexOf(obtenirProduitParId(produitMaj.getId()));
        if (index != -1) {
            produits.set(index, produitMaj);
        } else {
            throw new IllegalArgumentException("Produit non trouvé avec l'ID : " + produitMaj.getId());
        }
    }

    // Opération DELETE
    public void supprimerProduitParId(Long id) {
        produits.removeIf(produit -> produit.getId().equals(id));
    }

    // Validation de l'unicité du produit
    private void validerUniciteProduit(Produit produit) {
        if (produits.stream().anyMatch(p -> p.getId().equals(produit.getId()) || p.getNom().equals(produit.getNom()))) {
            throw new IllegalArgumentException("Un produit avec le même ID ou nom existe déjà.");
        }
    }

    // Validation des données du produit
    private void validerDonneesProduit(Produit produit) {
        if (produit.getPrix() < 0 || produit.getQuantite() < 0) {
            throw new IllegalArgumentException("Le prix et la quantité des produits doivent être positifs.");
        }
    }

    // Vous pouvez ajouter d'autres méthodes ou fonctionnalités selon vos besoins
}

