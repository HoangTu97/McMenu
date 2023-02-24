import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './ingredients.reducer';

export const IngredientsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const ingredientsEntity = useAppSelector(state => state.ingredients.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="ingredientsDetailsHeading">
          <Translate contentKey="mcMenuApp.ingredients.detail.title">Ingredients</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="mcMenuApp.ingredients.name">Name</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.name}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="mcMenuApp.ingredients.imageUrl">Image Url</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.imageUrl}</dd>
          <dt>
            <span id="insideIngredients">
              <Translate contentKey="mcMenuApp.ingredients.insideIngredients">Inside Ingredients</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.insideIngredients}</dd>
          <dt>
            <span id="insideContains">
              <Translate contentKey="mcMenuApp.ingredients.insideContains">Inside Contains</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.insideContains}</dd>
          <dt>
            <span id="mayContains">
              <Translate contentKey="mcMenuApp.ingredients.mayContains">May Contains</Translate>
            </span>
          </dt>
          <dd>{ingredientsEntity.mayContains}</dd>
        </dl>
        <Button tag={Link} to="/ingredients" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ingredients/${ingredientsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IngredientsDetail;
