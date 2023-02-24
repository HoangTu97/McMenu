import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './nutrition-summary.reducer';

export const NutritionSummaryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const nutritionSummaryEntity = useAppSelector(state => state.nutritionSummary.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="nutritionSummaryDetailsHeading">
          <Translate contentKey="mcMenuApp.nutritionSummary.detail.title">NutritionSummary</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{nutritionSummaryEntity.id}</dd>
          <dt>
            <span id="key">
              <Translate contentKey="mcMenuApp.nutritionSummary.key">Key</Translate>
            </span>
          </dt>
          <dd>{nutritionSummaryEntity.key}</dd>
          <dt>
            <span id="quantityMg">
              <Translate contentKey="mcMenuApp.nutritionSummary.quantityMg">Quantity Mg</Translate>
            </span>
          </dt>
          <dd>{nutritionSummaryEntity.quantityMg}</dd>
          <dt>
            <span id="percentDailyValues">
              <Translate contentKey="mcMenuApp.nutritionSummary.percentDailyValues">Percent Daily Values</Translate>
            </span>
          </dt>
          <dd>{nutritionSummaryEntity.percentDailyValues}</dd>
          <dt>
            <Translate contentKey="mcMenuApp.nutritionSummary.product">Product</Translate>
          </dt>
          <dd>{nutritionSummaryEntity.product ? nutritionSummaryEntity.product.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/nutrition-summary" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/nutrition-summary/${nutritionSummaryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default NutritionSummaryDetail;
