import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIngredients } from 'app/shared/model/ingredients.model';
import { getEntities as getIngredients } from 'app/entities/ingredients/ingredients.reducer';
import { ICategory } from 'app/shared/model/category.model';
import { getEntities as getCategories } from 'app/entities/category/category.reducer';
import { IMeal } from 'app/shared/model/meal.model';
import { getEntities as getMeals } from 'app/entities/meal/meal.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntity, updateEntity, createEntity, reset } from './product.reducer';

export const ProductUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const ingredients = useAppSelector(state => state.ingredients.entities);
  const categories = useAppSelector(state => state.category.entities);
  const meals = useAppSelector(state => state.meal.entities);
  const productEntity = useAppSelector(state => state.product.entity);
  const loading = useAppSelector(state => state.product.loading);
  const updating = useAppSelector(state => state.product.updating);
  const updateSuccess = useAppSelector(state => state.product.updateSuccess);

  const handleClose = () => {
    navigate('/product');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getIngredients({}));
    dispatch(getCategories({}));
    dispatch(getMeals({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...productEntity,
      ...values,
      ingredients: mapIdList(values.ingredients),
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
          ...productEntity,
          ingredients: productEntity?.ingredients?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="testMcMenuApp.product.home.createOrEditLabel" data-cy="ProductCreateUpdateHeading">
            Create or edit a Product
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? <ValidatedField name="id" required readOnly id="product-id" label="ID" validate={{ required: true }} /> : null}
              <ValidatedField
                label="Name"
                id="product-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 255, message: 'This field cannot be longer than 255 characters.' },
                }}
              />
              <ValidatedField
                label="Image Url"
                id="product-imageUrl"
                name="imageUrl"
                data-cy="imageUrl"
                type="text"
                validate={{
                  required: { value: true, message: 'This field is required.' },
                  maxLength: { value: 255, message: 'This field cannot be longer than 255 characters.' },
                }}
              />
              <ValidatedField
                label="Description"
                id="product-description"
                name="description"
                data-cy="description"
                type="text"
                validate={{
                  maxLength: { value: 5000, message: 'This field cannot be longer than 5000 characters.' },
                }}
              />
              <ValidatedField
                label="Is Limited Time Only"
                id="product-isLimitedTimeOnly"
                name="isLimitedTimeOnly"
                data-cy="isLimitedTimeOnly"
                check
                type="checkbox"
              />
              <ValidatedField
                label="Related Product Id"
                id="product-relatedProductId"
                name="relatedProductId"
                data-cy="relatedProductId"
                type="text"
              />
              <ValidatedField
                label="Display Order"
                id="product-displayOrder"
                name="displayOrder"
                data-cy="displayOrder"
                type="text"
                validate={{
                  min: { value: 0, message: 'This field should be at least 0.' },
                  validate: v => isNumber(v) || 'This field should be a number.',
                }}
              />
              <ValidatedField label="Label" id="product-label" name="label" data-cy="label" type="text" />
              <ValidatedField label="Abbr Label" id="product-abbrLabel" name="abbrLabel" data-cy="abbrLabel" type="text" />
              <ValidatedField label="Is Default" id="product-isDefault" name="isDefault" data-cy="isDefault" check type="checkbox" />
              <ValidatedField label="Ingredients" id="product-ingredients" data-cy="ingredients" type="select" multiple name="ingredients">
                <option value="" key="0" />
                {ingredients
                  ? ingredients.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/product" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">Back</span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp; Save
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default ProductUpdate;
