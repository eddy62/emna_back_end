import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown
    icon="th-list"
    name={translate('global.menu.entities.main')}
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/facture">
      <Translate contentKey="global.menu.entities.facture" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/client-fournisseur">
      <Translate contentKey="global.menu.entities.clientFournisseur" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/produit">
      <Translate contentKey="global.menu.entities.produit" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/operation">
      <Translate contentKey="global.menu.entities.operation" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/releve">
      <Translate contentKey="global.menu.entities.releve" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/etat-releve">
      <Translate contentKey="global.menu.entities.etatReleve" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/devis">
      <Translate contentKey="global.menu.entities.devis" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/contrat">
      <Translate contentKey="global.menu.entities.contrat" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/avenant">
      <Translate contentKey="global.menu.entities.avenant" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/clause">
      <Translate contentKey="global.menu.entities.clause" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/article">
      <Translate contentKey="global.menu.entities.article" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/adresse">
      <Translate contentKey="global.menu.entities.adresse" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/employe">
      <Translate contentKey="global.menu.entities.employe" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/type-absence">
      <Translate contentKey="global.menu.entities.typeAbsence" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/absence">
      <Translate contentKey="global.menu.entities.absence" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/type-prime">
      <Translate contentKey="global.menu.entities.typePrime" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/prime">
      <Translate contentKey="global.menu.entities.prime" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/fiche-paie">
      <Translate contentKey="global.menu.entities.fichePaie" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/heures-supplementaires">
      <Translate contentKey="global.menu.entities.heuresSupplementaires" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/note-de-frais">
      <Translate contentKey="global.menu.entities.noteDeFrais" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/autres-variable">
      <Translate contentKey="global.menu.entities.autresVariable" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/etat-facture">
      <Translate contentKey="global.menu.entities.etatFacture" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/etat-devis">
      <Translate contentKey="global.menu.entities.etatDevis" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/comptable">
      <Translate contentKey="global.menu.entities.comptable" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/societe">
      <Translate contentKey="global.menu.entities.societe" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/info-entreprise">
      <Translate contentKey="global.menu.entities.infoEntreprise" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/statut-employe">
      <Translate contentKey="global.menu.entities.statutEmploye" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
