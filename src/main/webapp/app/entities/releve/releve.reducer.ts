import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IReleve, defaultValue } from 'app/shared/model/releve.model';

export const ACTION_TYPES = {
  FETCH_RELEVE_LIST: 'releve/FETCH_RELEVE_LIST',
  FETCH_RELEVE: 'releve/FETCH_RELEVE',
  CREATE_RELEVE: 'releve/CREATE_RELEVE',
  UPDATE_RELEVE: 'releve/UPDATE_RELEVE',
  DELETE_RELEVE: 'releve/DELETE_RELEVE',
  RESET: 'releve/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IReleve>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ReleveState = Readonly<typeof initialState>;

// Reducer

export default (state: ReleveState = initialState, action): ReleveState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_RELEVE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_RELEVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_RELEVE):
    case REQUEST(ACTION_TYPES.UPDATE_RELEVE):
    case REQUEST(ACTION_TYPES.DELETE_RELEVE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_RELEVE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_RELEVE):
    case FAILURE(ACTION_TYPES.CREATE_RELEVE):
    case FAILURE(ACTION_TYPES.UPDATE_RELEVE):
    case FAILURE(ACTION_TYPES.DELETE_RELEVE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELEVE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_RELEVE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_RELEVE):
    case SUCCESS(ACTION_TYPES.UPDATE_RELEVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_RELEVE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/releves';

// Actions

export const getEntities: ICrudGetAllAction<IReleve> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_RELEVE_LIST,
  payload: axios.get<IReleve>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IReleve> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_RELEVE,
    payload: axios.get<IReleve>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IReleve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_RELEVE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IReleve> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_RELEVE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IReleve> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_RELEVE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
