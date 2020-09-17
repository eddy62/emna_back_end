import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAvenant, defaultValue } from 'app/shared/model/avenant.model';

export const ACTION_TYPES = {
  FETCH_AVENANT_LIST: 'avenant/FETCH_AVENANT_LIST',
  FETCH_AVENANT: 'avenant/FETCH_AVENANT',
  CREATE_AVENANT: 'avenant/CREATE_AVENANT',
  UPDATE_AVENANT: 'avenant/UPDATE_AVENANT',
  DELETE_AVENANT: 'avenant/DELETE_AVENANT',
  RESET: 'avenant/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAvenant>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type AvenantState = Readonly<typeof initialState>;

// Reducer

export default (state: AvenantState = initialState, action): AvenantState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AVENANT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AVENANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_AVENANT):
    case REQUEST(ACTION_TYPES.UPDATE_AVENANT):
    case REQUEST(ACTION_TYPES.DELETE_AVENANT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_AVENANT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AVENANT):
    case FAILURE(ACTION_TYPES.CREATE_AVENANT):
    case FAILURE(ACTION_TYPES.UPDATE_AVENANT):
    case FAILURE(ACTION_TYPES.DELETE_AVENANT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVENANT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_AVENANT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_AVENANT):
    case SUCCESS(ACTION_TYPES.UPDATE_AVENANT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_AVENANT):
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

const apiUrl = 'api/avenants';

// Actions

export const getEntities: ICrudGetAllAction<IAvenant> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AVENANT_LIST,
  payload: axios.get<IAvenant>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IAvenant> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AVENANT,
    payload: axios.get<IAvenant>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IAvenant> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AVENANT,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAvenant> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AVENANT,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAvenant> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AVENANT,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
