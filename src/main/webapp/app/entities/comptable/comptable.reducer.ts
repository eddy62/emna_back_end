import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IComptable, defaultValue } from 'app/shared/model/comptable.model';

export const ACTION_TYPES = {
  FETCH_COMPTABLE_LIST: 'comptable/FETCH_COMPTABLE_LIST',
  FETCH_COMPTABLE: 'comptable/FETCH_COMPTABLE',
  CREATE_COMPTABLE: 'comptable/CREATE_COMPTABLE',
  UPDATE_COMPTABLE: 'comptable/UPDATE_COMPTABLE',
  DELETE_COMPTABLE: 'comptable/DELETE_COMPTABLE',
  RESET: 'comptable/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IComptable>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ComptableState = Readonly<typeof initialState>;

// Reducer

export default (state: ComptableState = initialState, action): ComptableState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_COMPTABLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_COMPTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_COMPTABLE):
    case REQUEST(ACTION_TYPES.UPDATE_COMPTABLE):
    case REQUEST(ACTION_TYPES.DELETE_COMPTABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_COMPTABLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_COMPTABLE):
    case FAILURE(ACTION_TYPES.CREATE_COMPTABLE):
    case FAILURE(ACTION_TYPES.UPDATE_COMPTABLE):
    case FAILURE(ACTION_TYPES.DELETE_COMPTABLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMPTABLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_COMPTABLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_COMPTABLE):
    case SUCCESS(ACTION_TYPES.UPDATE_COMPTABLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_COMPTABLE):
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

const apiUrl = 'api/comptables';

// Actions

export const getEntities: ICrudGetAllAction<IComptable> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_COMPTABLE_LIST,
  payload: axios.get<IComptable>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IComptable> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_COMPTABLE,
    payload: axios.get<IComptable>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IComptable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_COMPTABLE,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IComptable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_COMPTABLE,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IComptable> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_COMPTABLE,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
