/* tslint:disable no-unused-expression */
import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  ParametersOfContentTypeComponentsPage,
  ParametersOfContentTypeDeleteDialog,
  ParametersOfContentTypeUpdatePage
} from './parameters-of-content-type.page-object';

const expect = chai.expect;

describe('ParametersOfContentType e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let parametersOfContentTypeUpdatePage: ParametersOfContentTypeUpdatePage;
  let parametersOfContentTypeComponentsPage: ParametersOfContentTypeComponentsPage;
  let parametersOfContentTypeDeleteDialog: ParametersOfContentTypeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ParametersOfContentTypes', async () => {
    await navBarPage.goToEntity('parameters-of-content-type');
    parametersOfContentTypeComponentsPage = new ParametersOfContentTypeComponentsPage();
    await browser.wait(ec.visibilityOf(parametersOfContentTypeComponentsPage.title), 5000);
    expect(await parametersOfContentTypeComponentsPage.getTitle()).to.eq('jhipsterApp.parametersOfContentType.home.title');
  });

  it('should load create ParametersOfContentType page', async () => {
    await parametersOfContentTypeComponentsPage.clickOnCreateButton();
    parametersOfContentTypeUpdatePage = new ParametersOfContentTypeUpdatePage();
    expect(await parametersOfContentTypeUpdatePage.getPageTitle()).to.eq('jhipsterApp.parametersOfContentType.home.createOrEditLabel');
    await parametersOfContentTypeUpdatePage.cancel();
  });

  it('should create and save ParametersOfContentTypes', async () => {
    const nbButtonsBeforeCreate = await parametersOfContentTypeComponentsPage.countDeleteButtons();

    await parametersOfContentTypeComponentsPage.clickOnCreateButton();
    await promise.all([]);
    await parametersOfContentTypeUpdatePage.save();
    expect(await parametersOfContentTypeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await parametersOfContentTypeComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ParametersOfContentType', async () => {
    const nbButtonsBeforeDelete = await parametersOfContentTypeComponentsPage.countDeleteButtons();
    await parametersOfContentTypeComponentsPage.clickOnLastDeleteButton();

    parametersOfContentTypeDeleteDialog = new ParametersOfContentTypeDeleteDialog();
    expect(await parametersOfContentTypeDeleteDialog.getDialogTitle()).to.eq('jhipsterApp.parametersOfContentType.delete.question');
    await parametersOfContentTypeDeleteDialog.clickOnConfirmButton();

    expect(await parametersOfContentTypeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
