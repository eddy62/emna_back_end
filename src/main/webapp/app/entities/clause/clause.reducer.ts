import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IClause, defaultValue } from 'app/shared/model/clause.model';

export const ACTION_TYPES = {
  FETCH_CLAUSE_LIST: 'clause/FETCH_CLAUSE_LIST',
  FETCH_CLAUSE: 'clause/FETCH_CLAUSE',
  CREATE_CLAUSE: 'clause/CREATE_CLAUSE',
  UPDATE_CLAUSE: 'clause/UPDATE_CLAUSE',
  DELETE_CLAUSE: 'clause/DELETE_CLAUSE',
  RESET: 'clause/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IClause>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type ClauseState = Readonly<typeof initialState>;

// Reducer

export default (state: ClauseState = initialState, action): ClauseState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_CLAUSE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_CLAUSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_CLAUSE):
    case REQUEST(ACTION_TYPES.UPDATE_CLAUSE):
    case REQUEST(ACTION_TYPES.DELETE_CLAUSE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_CLAUSE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_CLAUSE):
    case FAILURE(ACTION_TYPES.CREATE_CLAUSE):
    case FAILURE(ACTION_TYPES.UPDATE_CLAUSE):
    case FAILURE(ACTION_TYPES.DELETE_CLAUSE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLAUSE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_CLAUSE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_CLAUSE):
    case SUCCESS(ACTION_TYPES.UPDATE_CLAUSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_CLAUSE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {}
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState
      };
    default:
      return state;
  }
};

const apiUrl = 'api/clauses';

// Actions

export const getEntities: ICrudGetAllAction<IClause> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_CLAUSE_LIST,
  payload: axios.get<IClause>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IClause> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_CLAUSE,
    payload: axios.get<IClause>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IClause> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_CLAUSE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IClause> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_CLAUSE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IClause> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_CLAUSE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
