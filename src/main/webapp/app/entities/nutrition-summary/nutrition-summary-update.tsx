import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { INutritionSummary } from 'app/shared/model/nutrition-summary.model';
import { NutritionKey } from 'app/shared/model/enumerations/nutrition-key.model';
import { getEntity, updateEntity, createEntity, reset } from './nutrition-summary.reducer';

export const NutritionSummaryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const products = useAppSelector(state => state.product.entities);
  const nutritionSummaryEntity = useAppSelector(state => state.nutritionSummary.entity);
  const loading = useAppSelector(state => state.nutritionSummary.loading);
  const updating = useAppSelector(state => state.nutritionSummary.updating);
  const updateSuccess = useAppSelector(state => state.nutritionSummary.updateSuccess);
  const nutritionKeyValues = Object.keys(NutritionKey);

  const handleClose = () => {
    navigate('/nutrition-summary');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getProducts({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...nutritionSummaryEntity,
      ...values,
      product: products.find(it => it.id.toString() === values.product.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          key: 'CALORIES',
          ...nutritionSummaryEntity,
          product: nutritionSummaryEntity?.product?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="mcMenuApp.nutritionSummary.home.createOrEditLabel" data-cy="NutritionSummaryCreateUpdateHeading">
            <Translate contentKey="mcMenuApp.nutritionSummary.home.createOrEditLabel">Create or edit a NutritionSummary</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="nutrition-summary-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('mcMenuApp.nutritionSummary.key')}
                id="nutrition-summary-key"
                name="key"
                data-cy="key"
                type="select"
              >
                {nutritionKeyValues.map(nutritionKey => (
                  <option value={nutritionKey} key={nutritionKey}>
                    {translate('mcMenuApp.NutritionKey.' + nutritionKey)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('mcMenuApp.nutritionSummary.quantityMg')}
                id="nutrition-summary-quantityMg"
                name="quantityMg"
                data-cy="quantityMg"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('mcMenuApp.nutritionSummary.percentDailyValues')}
                id="nutrition-summary-percentDailyValues"
                name="percentDailyValues"
                data-cy="percentDailyValues"
                type="text"
                validate={{
                  min: { value: 0, message: translate('entity.validation.min', { min: 0 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                id="nutrition-summary-product"
                name="product"
                data-cy="product"
                label={translate('mcMenuApp.nutritionSummary.product')}
                type="select"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/nutrition-summary" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default NutritionSummaryUpdate;
