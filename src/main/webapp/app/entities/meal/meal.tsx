import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IMeal } from 'app/shared/model/meal.model';
import { searchEntities, getEntities } from './meal.reducer';

export const Meal = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const mealList = useAppSelector(state => state.meal.entities);
  const loading = useAppSelector(state => state.meal.loading);

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
      <h2 id="meal-heading" data-cy="MealHeading">
        <Translate contentKey="mcMenuApp.meal.home.title">Meals</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mcMenuApp.meal.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/meal/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mcMenuApp.meal.home.createLabel">Create new Meal</Translate>
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
                  placeholder={translate('mcMenuApp.meal.home.search')}
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
        {mealList && mealList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mcMenuApp.meal.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.meal.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.meal.imageUrl">Image Url</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.meal.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.meal.product">Product</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {mealList.map((meal, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/meal/${meal.id}`} color="link" size="sm">
                      {meal.id}
                    </Button>
                  </td>
                  <td>{meal.name}</td>
                  <td>{meal.imageUrl}</td>
                  <td>{meal.description}</td>
                  <td>
                    {meal.products
                      ? meal.products.map((val, j) => (
                          <span key={j}>
                            <Link to={`/product/${val.id}`}>{val.id}</Link>
                            {j === meal.products.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/meal/${meal.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/meal/${meal.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/meal/${meal.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="mcMenuApp.meal.home.notFound">No Meals found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Meal;
