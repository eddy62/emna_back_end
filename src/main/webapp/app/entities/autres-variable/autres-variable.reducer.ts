import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IAutresVariable, defaultValue } from 'app/shared/model/autres-variable.model';

export const ACTION_TYPES = {
  FETCH_AUTRESVARIABLE_LIST: 'autresVariable/FETCH_AUTRESVARIABLE_LIST',
  FETCH_AUTRESVARIABLE: 'autresVariable/FETCH_AUTRESVARIABLE',
  CREATE_AUTRESVARIABLE: 'autresVariable/CREATE_AUTRESVARIABLE',
  UPDATE_AUTRESVARIABLE: 'autresVariable/UPDATE_AUTRESVARIABLE',
  DELETE_AUTRESVARIABLE: 'autresVariable/DELETE_AUTRESVARIABLE',
  RESET: 'autresVariable/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IAutresVariable>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type AutresVariableState = Readonly<typeof initialState>;

// Reducer

export default (state: AutresVariableState = initialState, action): AutresVariableState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_AUTRESVARIABLE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_AUTRESVARIABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_AUTRESVARIABLE):
    case REQUEST(ACTION_TYPES.UPDATE_AUTRESVARIABLE):
    case REQUEST(ACTION_TYPES.DELETE_AUTRESVARIABLE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_AUTRESVARIABLE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_AUTRESVARIABLE):
    case FAILURE(ACTION_TYPES.CREATE_AUTRESVARIABLE):
    case FAILURE(ACTION_TYPES.UPDATE_AUTRESVARIABLE):
    case FAILURE(ACTION_TYPES.DELETE_AUTRESVARIABLE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_AUTRESVARIABLE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_AUTRESVARIABLE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_AUTRESVARIABLE):
    case SUCCESS(ACTION_TYPES.UPDATE_AUTRESVARIABLE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_AUTRESVARIABLE):
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

const apiUrl = 'api/autres-variables';

// Actions

export const getEntities: ICrudGetAllAction<IAutresVariable> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_AUTRESVARIABLE_LIST,
  payload: axios.get<IAutresVariable>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<IAutresVariable> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_AUTRESVARIABLE,
    payload: axios.get<IAutresVariable>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<IAutresVariable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_AUTRESVARIABLE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IAutresVariable> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_AUTRESVARIABLE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IAutresVariable> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_AUTRESVARIABLE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
