import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './meal.reducer';

export const MealDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const mealEntity = useAppSelector(state => state.meal.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="mealDetailsHeading">
          <Translate contentKey="mcMenuApp.meal.detail.title">Meal</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{mealEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="mcMenuApp.meal.name">Name</Translate>
            </span>
          </dt>
          <dd>{mealEntity.name}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="mcMenuApp.meal.imageUrl">Image Url</Translate>
            </span>
          </dt>
          <dd>{mealEntity.imageUrl}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="mcMenuApp.meal.description">Description</Translate>
            </span>
          </dt>
          <dd>{mealEntity.description}</dd>
          <dt>
            <Translate contentKey="mcMenuApp.meal.product">Product</Translate>
          </dt>
          <dd>
            {mealEntity.products
              ? mealEntity.products.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {mealEntity.products && i === mealEntity.products.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/meal" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/meal/${mealEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default MealDetail;
