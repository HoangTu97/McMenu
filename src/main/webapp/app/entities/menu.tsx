import MenuItem from 'app/shared/layout/menus/menu-item';
import React from 'react';
import { Translate } from 'react-jhipster';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/category">
        <Translate contentKey="global.menu.entities.category" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/meal">
        <Translate contentKey="global.menu.entities.meal" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product">
        <Translate contentKey="global.menu.entities.product" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/nutrition-summary">
        <Translate contentKey="global.menu.entities.nutritionSummary" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/ingredients">
        <Translate contentKey="global.menu.entities.ingredients" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
