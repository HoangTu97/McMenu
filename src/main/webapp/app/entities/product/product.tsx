import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProduct } from 'app/shared/model/product.model';
import { searchEntities, getEntities } from './product.reducer';

export const Product = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const productList = useAppSelector(state => state.product.entities);
  const loading = useAppSelector(state => state.product.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const startSearching = e => {
    if (search) {
      dispatch(searchEntities({ query: search }));
    }
    e.preventDefault();
  };

  const clear = () => {
    setSearch('');
    dispatch(getEntities({}));
  };

  const handleSearch = event => setSearch(event.target.value);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="product-heading" data-cy="ProductHeading">
        <Translate contentKey="mcMenuApp.product.home.title">Products</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mcMenuApp.product.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/product/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mcMenuApp.product.home.createLabel">Create new Product</Translate>
          </Link>
        </div>
      </h2>
      <Row>
        <Col sm="12">
          <Form onSubmit={startSearching}>
            <FormGroup>
              <InputGroup>
                <Input
                  type="text"
                  name="search"
                  defaultValue={search}
                  onChange={handleSearch}
                  placeholder={translate('mcMenuApp.product.home.search')}
                />
                <Button className="input-group-addon">
                  <FontAwesomeIcon icon="search" />
                </Button>
                <Button type="reset" className="input-group-addon" onClick={clear}>
                  <FontAwesomeIcon icon="trash" />
                </Button>
              </InputGroup>
            </FormGroup>
          </Form>
        </Col>
      </Row>
      <div className="table-responsive">
        {productList && productList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mcMenuApp.product.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.imageUrl">Image Url</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.isLimitedTimeOnly">Is Limited Time Only</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.relatedProductId">Related Product Id</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.displayOrder">Display Order</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.label">Label</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.abbrLabel">Abbr Label</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.isDefault">Is Default</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.product.ingredients">Ingredients</Translate>
                </th>
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
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/product/${product.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/product/${product.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="mcMenuApp.product.home.notFound">No Products found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Product;
