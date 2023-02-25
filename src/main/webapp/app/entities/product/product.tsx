import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { getEntities } from './product.reducer';

export const Product = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const productList = useAppSelector(state => state.product.entities);
  const loading = useAppSelector(state => state.product.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="product-heading" data-cy="ProductHeading">
        Products
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} /> Refresh list
          </Button>
          <Link to="/product/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp; Create a new Product
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {productList && productList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Image Url</th>
                <th>Description</th>
                <th>Is Limited Time Only</th>
                <th>Related Product Id</th>
                <th>Display Order</th>
                <th>Label</th>
                <th>Abbr Label</th>
                <th>Is Default</th>
                <th>Ingredients</th>
                <th />
              </tr>
            </thead>
            <tbody>
              {productList.map((product, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/product/${product.id}`} color="link" size="sm">
                      {product.id}
                    </Button>
                  </td>
                  <td>{product.name}</td>
                  <td>{product.imageUrl}</td>
                  <td>{product.description}</td>
                  <td>{product.isLimitedTimeOnly ? 'true' : 'false'}</td>
                  <td>{product.relatedProductId}</td>
                  <td>{product.displayOrder}</td>
                  <td>{product.label}</td>
                  <td>{product.abbrLabel}</td>
                  <td>{product.isDefault ? 'true' : 'false'}</td>
                  <td>
                    {product.ingredients
                      ? product.ingredients.map((val, j) => (
                          <span key={j}>
                            <Link to={`/ingredients/${val.id}`}>{val.id}</Link>
                            {j === product.ingredients.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/product/${product.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" /> <span className="d-none d-md-inline">View</span>
                      </Button>
                      <Button tag={Link} to={`/product/${product.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" /> <span className="d-none d-md-inline">Edit</span>
                      </Button>
                      <Button tag={Link} to={`/product/${product.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" /> <span className="d-none d-md-inline">Delete</span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && <div className="alert alert-warning">No Products found</div>
        )}
      </div>
    </div>
  );
};

export default Product;
