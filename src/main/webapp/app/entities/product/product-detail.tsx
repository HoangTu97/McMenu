import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">
          <Translate contentKey="mcMenuApp.product.detail.title">Product</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="mcMenuApp.product.name">Name</Translate>
            </span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="imageUrl">
              <Translate contentKey="mcMenuApp.product.imageUrl">Image Url</Translate>
            </span>
          </dt>
          <dd>{productEntity.imageUrl}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="mcMenuApp.product.description">Description</Translate>
            </span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="isLimitedTimeOnly">
              <Translate contentKey="mcMenuApp.product.isLimitedTimeOnly">Is Limited Time Only</Translate>
            </span>
          </dt>
          <dd>{productEntity.isLimitedTimeOnly ? 'true' : 'false'}</dd>
          <dt>
            <span id="relatedProductId">
              <Translate contentKey="mcMenuApp.product.relatedProductId">Related Product Id</Translate>
            </span>
          </dt>
          <dd>{productEntity.relatedProductId}</dd>
          <dt>
            <span id="displayOrder">
              <Translate contentKey="mcMenuApp.product.displayOrder">Display Order</Translate>
            </span>
          </dt>
          <dd>{productEntity.displayOrder}</dd>
          <dt>
            <span id="label">
              <Translate contentKey="mcMenuApp.product.label">Label</Translate>
            </span>
          </dt>
          <dd>{productEntity.label}</dd>
          <dt>
            <span id="abbrLabel">
              <Translate contentKey="mcMenuApp.product.abbrLabel">Abbr Label</Translate>
            </span>
          </dt>
          <dd>{productEntity.abbrLabel}</dd>
          <dt>
            <span id="isDefault">
              <Translate contentKey="mcMenuApp.product.isDefault">Is Default</Translate>
            </span>
          </dt>
          <dd>{productEntity.isDefault ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="mcMenuApp.product.ingredients">Ingredients</Translate>
          </dt>
          <dd>
            {productEntity.ingredients
              ? productEntity.ingredients.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {productEntity.ingredients && i === productEntity.ingredients.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/product" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
