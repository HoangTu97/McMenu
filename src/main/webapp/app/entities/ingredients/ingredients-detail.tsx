import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
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
        <h2 data-cy="ingredientsDetailsHeading">Ingredients</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{ingredientsEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{ingredientsEntity.name}</dd>
          <dt>
            <span id="imageUrl">Image Url</span>
          </dt>
          <dd>{ingredientsEntity.imageUrl}</dd>
          <dt>
            <span id="insideIngredients">Inside Ingredients</span>
          </dt>
          <dd>{ingredientsEntity.insideIngredients}</dd>
          <dt>
            <span id="insideContains">Inside Contains</span>
          </dt>
          <dd>{ingredientsEntity.insideContains}</dd>
          <dt>
            <span id="mayContains">May Contains</span>
          </dt>
          <dd>{ingredientsEntity.mayContains}</dd>
        </dl>
        <Button tag={Link} to="/ingredients" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/ingredients/${ingredientsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default IngredientsDetail;
