import axios from 'axios';
import { ICrudDeleteAction, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { FAILURE, REQUEST, SUCCESS } from 'app/shared/reducers/action-type.util';

import { defaultValue, ITypeDocument } from 'app/shared/model/type-document.model';

export const ACTION_TYPES = {
  FETCH_TYPEDOCUMENT_LIST: 'typeDocument/FETCH_TYPEDOCUMENT_LIST',
  FETCH_TYPEDOCUMENT: 'typeDocument/FETCH_TYPEDOCUMENT',
  CREATE_TYPEDOCUMENT: 'typeDocument/CREATE_TYPEDOCUMENT',
  UPDATE_TYPEDOCUMENT: 'typeDocument/UPDATE_TYPEDOCUMENT',
  DELETE_TYPEDOCUMENT: 'typeDocument/DELETE_TYPEDOCUMENT',
  RESET: 'typeDocument/RESET'
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITypeDocument>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false
};

export type TypeDocumentState = Readonly<typeof initialState>;

// Reducer

export default (state: TypeDocumentState = initialState, action): TypeDocumentState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TYPEDOCUMENT_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TYPEDOCUMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true
      };
    case REQUEST(ACTION_TYPES.CREATE_TYPEDOCUMENT):
    case REQUEST(ACTION_TYPES.UPDATE_TYPEDOCUMENT):
    case REQUEST(ACTION_TYPES.DELETE_TYPEDOCUMENT):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true
      };
    case FAILURE(ACTION_TYPES.FETCH_TYPEDOCUMENT_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TYPEDOCUMENT):
    case FAILURE(ACTION_TYPES.CREATE_TYPEDOCUMENT):
    case FAILURE(ACTION_TYPES.UPDATE_TYPEDOCUMENT):
    case FAILURE(ACTION_TYPES.DELETE_TYPEDOCUMENT):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEDOCUMENT_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.FETCH_TYPEDOCUMENT):
      return {
        ...state,
        loading: false,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.CREATE_TYPEDOCUMENT):
    case SUCCESS(ACTION_TYPES.UPDATE_TYPEDOCUMENT):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data
      };
    case SUCCESS(ACTION_TYPES.DELETE_TYPEDOCUMENT):
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

const apiUrl = 'api/type-documents';

// Actions

export const getEntities: ICrudGetAllAction<ITypeDocument> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TYPEDOCUMENT_LIST,
  payload: axios.get<ITypeDocument>(`${apiUrl}?cacheBuster=${new Date().getTime()}`)
});

export const getEntity: ICrudGetAction<ITypeDocument> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TYPEDOCUMENT,
    payload: axios.get<ITypeDocument>(requestUrl)
  };
};

export const createEntity: ICrudPutAction<ITypeDocument> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TYPEDOCUMENT,
    payload: axios.post(apiUrl, cleanEntity(entity))
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITypeDocument> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TYPEDOCUMENT,
    payload: axios.put(apiUrl, cleanEntity(entity))
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITypeDocument> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TYPEDOCUMENT,
    payload: axios.delete(requestUrl)
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET
});
