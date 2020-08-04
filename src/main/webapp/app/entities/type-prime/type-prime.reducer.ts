import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypePrime, defaultValue } from 'app/shared/model/type-prime.model';

export const ACTION_TYPES = {
  FETCH_TYPEPRIME_LIST: 'typePrime/FETCH_TYPEPRIME_LIST',
  FETCH_TYPEPRIME: 'typePrime/FETCH_TYPEPRIME',
  CREATE_TYPEPRIME: 'typePrime/CREATE_TYPEPRIME',
  UPDATE_TYPEPRIME: 'typePrime/UPDATE_TYPEPRIME',
  DELETE_TYPEPRIME: 'typePrime/DELETE_TYPEPRIME',
  RESET: 'typePrime/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypePrime>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TypePrimeState = Readonly<typeof initialState>;

// Reducer

export default (state: TypePrimeState = initialState, action): TypePrimeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPEPRIME_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEPRIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEPRIME):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEPRIME):
    case REQUEST(ACTION_TYPES.DELETE_TYPEPRIME):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPEPRIME_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEPRIME):
    case FAILURE(ACTION_TYPES.CREATE_TYPEPRIME):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEPRIME):
    case FAILURE(ACTION_TYPES.DELETE_TYPEPRIME):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPRIME_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEPRIME):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEPRIME):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEPRIME):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEPRIME):
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

const apiUrl = 'api/type-primes';

// Actions

export const getEntities: ICrudGetAllAction<ITypePrime> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEPRIME_LIST,
  payload: axios.get<ITypePrime>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITypePrime> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEPRIME,
    payload: axios.get<ITypePrime>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITypePrime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEPRIME,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypePrime> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEPRIME,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypePrime> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEPRIME,
    payload: axios.delete(requestUrl)
  });
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
