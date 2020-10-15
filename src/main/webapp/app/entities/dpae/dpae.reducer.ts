import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IDpae, defaultValue } from 'app/shared/model/dpae.model';

export const ACTION_TYPES = {
  FETCH_DPAE_LIST: 'dpae/FETCH_DPAE_LIST',
  FETCH_DPAE: 'dpae/FETCH_DPAE',
  CREATE_DPAE: 'dpae/CREATE_DPAE',
  UPDATE_DPAE: 'dpae/UPDATE_DPAE',
  DELETE_DPAE: 'dpae/DELETE_DPAE',
  RESET: 'dpae/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IDpae>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type DpaeState = Readonly<typeof initialState>;

// Reducer

export default (state: DpaeState = initialState, action): DpaeState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_DPAE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_DPAE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_DPAE):
    case REQUEST(ACTION_TYPES.UPDATE_DPAE):
    case REQUEST(ACTION_TYPES.DELETE_DPAE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_DPAE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_DPAE):
    case FAILURE(ACTION_TYPES.CREATE_DPAE):
    case FAILURE(ACTION_TYPES.UPDATE_DPAE):
    case FAILURE(ACTION_TYPES.DELETE_DPAE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPAE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_DPAE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_DPAE):
    case SUCCESS(ACTION_TYPES.UPDATE_DPAE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_DPAE):
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

const apiUrl = 'api/dpaes';

// Actions

export const getEntities: ICrudGetAllAction<IDpae> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_DPAE_LIST,
  payload: axios.get<IDpae>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IDpae> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_DPAE,
    payload: axios.get<IDpae>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IDpae> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_DPAE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IDpae> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_DPAE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IDpae> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_DPAE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
