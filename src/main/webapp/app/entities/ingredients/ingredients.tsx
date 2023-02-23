import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIngredients } from 'app/shared/model/ingredients.model';
import { searchEntities, getEntities } from './ingredients.reducer';

export const Ingredients = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const ingredientsList = useAppSelector(state => state.ingredients.entities);
  const loading = useAppSelector(state => state.ingredients.loading);

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
      <h2 id="ingredients-heading" data-cy="IngredientsHeading">
        <Translate contentKey="mcMenuApp.ingredients.home.title">Ingredients</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="mcMenuApp.ingredients.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/ingredients/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="mcMenuApp.ingredients.home.createLabel">Create new Ingredients</Translate>
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
                  placeholder={translate('mcMenuApp.ingredients.home.search')}
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
        {ingredientsList && ingredientsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.name">Name</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.imageUrl">Image Url</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.insideIngredients">Inside Ingredients</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.insideContains">Inside Contains</Translate>
                </th>
                <th>
                  <Translate contentKey="mcMenuApp.ingredients.mayContains">May Contains</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {ingredientsList.map((ingredients, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/ingredients/${ingredients.id}`} color="link" size="sm">
                      {ingredients.id}
                    </Button>
                  </td>
                  <td>{ingredients.name}</td>
                  <td>{ingredients.imageUrl}</td>
                  <td>{ingredients.insideIngredients}</td>
                  <td>{ingredients.insideContains}</td>
                  <td>{ingredients.mayContains}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/ingredients/${ingredients.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ingredients/${ingredients.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/ingredients/${ingredients.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="mcMenuApp.ingredients.home.notFound">No Ingredients found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Ingredients;
