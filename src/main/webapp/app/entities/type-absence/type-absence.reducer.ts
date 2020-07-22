import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITypeAbsence, defaultValue } from 'app/shared/model/type-absence.model';

export const ACTION_TYPES = {
  FETCH_TYPEABSENCE_LIST: 'typeAbsence/FETCH_TYPEABSENCE_LIST',
  FETCH_TYPEABSENCE: 'typeAbsence/FETCH_TYPEABSENCE',
  CREATE_TYPEABSENCE: 'typeAbsence/CREATE_TYPEABSENCE',
  UPDATE_TYPEABSENCE: 'typeAbsence/UPDATE_TYPEABSENCE',
  DELETE_TYPEABSENCE: 'typeAbsence/DELETE_TYPEABSENCE',
  RESET: 'typeAbsence/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeAbsence>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TypeAbsenceState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeAbsenceState = initialState, action): TypeAbsenceState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPEABSENCE_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEABSENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEABSENCE):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEABSENCE):
    case REQUEST(ACTION_TYPES.DELETE_TYPEABSENCE):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPEABSENCE_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEABSENCE):
    case FAILURE(ACTION_TYPES.CREATE_TYPEABSENCE):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEABSENCE):
    case FAILURE(ACTION_TYPES.DELETE_TYPEABSENCE):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEABSENCE_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEABSENCE):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEABSENCE):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEABSENCE):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEABSENCE):
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

const apiUrl = 'api/type-absences';

// Actions

export const getEntities: ICrudGetAllAction<ITypeAbsence> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEABSENCE_LIST,
  payload: axios.get<ITypeAbsence>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITypeAbsence> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEABSENCE,
    payload: axios.get<ITypeAbsence>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITypeAbsence> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEABSENCE,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeAbsence> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEABSENCE,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeAbsence> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEABSENCE,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
