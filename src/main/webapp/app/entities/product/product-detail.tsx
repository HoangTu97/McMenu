import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import {} from 'react-jhipster';
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
        <h2 data-cy="productDetailsHeading">Product</h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">ID</span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="name">Name</span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="imageUrl">Image Url</span>
          </dt>
          <dd>{productEntity.imageUrl}</dd>
          <dt>
            <span id="description">Description</span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="isLimitedTimeOnly">Is Limited Time Only</span>
          </dt>
          <dd>{productEntity.isLimitedTimeOnly ? 'true' : 'false'}</dd>
          <dt>
            <span id="relatedProductId">Related Product Id</span>
          </dt>
          <dd>{productEntity.relatedProductId}</dd>
          <dt>
            <span id="displayOrder">Display Order</span>
          </dt>
          <dd>{productEntity.displayOrder}</dd>
          <dt>
            <span id="label">Label</span>
          </dt>
          <dd>{productEntity.label}</dd>
          <dt>
            <span id="abbrLabel">Abbr Label</span>
          </dt>
          <dd>{productEntity.abbrLabel}</dd>
          <dt>
            <span id="isDefault">Is Default</span>
          </dt>
          <dd>{productEntity.isDefault ? 'true' : 'false'}</dd>
          <dt>Ingredients</dt>
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
          <FontAwesomeIcon icon="arrow-left" /> <span className="d-none d-md-inline">Back</span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
