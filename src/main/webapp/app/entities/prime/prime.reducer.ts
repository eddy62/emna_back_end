import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IPrime, defaultValue } from 'app/shared/model/prime.model';

export const ACTION_TYPES = {
  FETCH_PRIME_LIST: 'prime/FETCH_PRIME_LIST',
  FETCH_PRIME: 'prime/FETCH_PRIME',
  CREATE_PRIME: 'prime/CREATE_PRIME',
  UPDATE_PRIME: 'prime/UPDATE_PRIME',
  DELETE_PRIME: 'prime/DELETE_PRIME',
  RESET: 'prime/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IPrime>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type PrimeState = Readonly<typeof initialState>;

// Reducer

export default (state: PrimeState = initialState, action): PrimeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PRIME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PRIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PRIME):
    case REQUEST(ACTION_TYPES.UPDATE_PRIME):
    case REQUEST(ACTION_TYPES.DELETE_PRIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PRIME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PRIME):
    case FAILURE(ACTION_TYPES.CREATE_PRIME):
    case FAILURE(ACTION_TYPES.UPDATE_PRIME):
    case FAILURE(ACTION_TYPES.DELETE_PRIME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRIME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRIME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PRIME):
    case SUCCESS(ACTION_TYPES.UPDATE_PRIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PRIME):
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

const apiUrl = 'api/primes';

// Actions

export const getEntities: ICrudGetAllAction<IPrime> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PRIME_LIST,
  payload: axios.get<IPrime>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IPrime> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PRIME,
    payload: axios.get<IPrime>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IPrime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PRIME,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IPrime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PRIME,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IPrime> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PRIME,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
